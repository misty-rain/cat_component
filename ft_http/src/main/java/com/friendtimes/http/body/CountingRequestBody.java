package com.friendtimes.http.body;

import com.mistyrain.okhttp.MediaType;
import com.mistyrain.okhttp.RequestBody;
import com.mistyrain.okhttp.okio.Buffer;
import com.mistyrain.okhttp.okio.BufferedSink;
import com.mistyrain.okhttp.okio.ForwardingSink;
import com.mistyrain.okhttp.okio.Okio;
import com.mistyrain.okhttp.okio.Sink;

import java.io.IOException;

/**
 * Decorates an OkHttp request body to count the number of bytes written when writing it. Can
 * decorate any request body, but is most useful for tracking the upload progress of large
 * multipart requests.
 *
 * @author Leo Nikkilä
 */
public class CountingRequestBody extends RequestBody {
    protected RequestBody delegate;
    protected Listener listener;

    protected CountingSink countingSink;

    public CountingRequestBody(RequestBody delegate, Listener listener) {
        this.delegate = delegate;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return delegate.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return delegate.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

        countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);

        delegate.writeTo(bufferedSink);

        bufferedSink.flush();
    }

    protected final class CountingSink extends ForwardingSink {

        private long bytesWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);

            bytesWritten += byteCount;
            listener.onRequestProgress(bytesWritten, contentLength());
        }

    }

    public static interface Listener {
        public void onRequestProgress(long bytesWritten, long contentLength);
    }
}
