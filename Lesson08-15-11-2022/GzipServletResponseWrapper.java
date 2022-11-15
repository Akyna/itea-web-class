package itea.web07;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GzipServletResponseWrapper extends HttpServletResponseWrapper {

	private GzipServletOutputStream gzipServletOutputStream;
	private PrintWriter out;

	public GzipServletResponseWrapper(HttpServletResponse response) {
		super(response);

	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {

		if (gzipServletOutputStream == null) {
			gzipServletOutputStream = new GzipServletOutputStream(getResponse().getOutputStream());
		}

		System.out.println("Gzip Wrapper intercepted output stream");
		
		return gzipServletOutputStream;
	}
	@Override
	public PrintWriter getWriter() throws IOException {
		
		if (out==null) {
			gzipServletOutputStream = new GzipServletOutputStream(getResponse().getOutputStream());
			System.out.println("Gzip Wrapper intercepted output stream - 2");
			out=new PrintWriter(new OutputStreamWriter(gzipServletOutputStream));
		}
		
		return out;
	}

	public void close() {
		if (out != null) {
			out.close();
		}

		if (gzipServletOutputStream != null) {
			try {
				gzipServletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void flushBuffer() throws IOException {

		if (out != null) {
			out.flush();
		}

		if (gzipServletOutputStream != null) {
			try {
				gzipServletOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		super.flushBuffer();
	}
}
