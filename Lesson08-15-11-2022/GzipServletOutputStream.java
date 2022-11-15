package itea.web07;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class GzipServletOutputStream extends ServletOutputStream {

	private GZIPOutputStream gzipOutputStream = null;

	public GzipServletOutputStream(OutputStream outputStream) throws IOException {
		gzipOutputStream = new GZIPOutputStream(outputStream);

	}

	@Override
	public boolean isReady() {

		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {

	}

	@Override
	public void write(int b) throws IOException {
		gzipOutputStream.write(b);

	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		gzipOutputStream.write(b, off, len);
	}

	@Override
	public void write(byte[] b) throws IOException {
		gzipOutputStream.write(b);
	}
	
	@Override
	public void close() throws IOException {
		gzipOutputStream.close();
	}
	
	@Override
	public void flush() throws IOException {
		gzipOutputStream.flush();
	}

}
