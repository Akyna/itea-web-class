package itea.web07;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GzipVtFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		if (acceptGzipEncoding(request)) {
			response.addHeader("Content-Encoding", "gzip");

			GzipServletResponseWrapper gzipServletResponseWrapper = new GzipServletResponseWrapper(response);
			arg2.doFilter(arg0, gzipServletResponseWrapper);
			gzipServletResponseWrapper.close();
		} else {
			arg2.doFilter(arg0, arg1);
		}
	}

	private boolean acceptGzipEncoding(HttpServletRequest request) {
		String acceptEncoding = request.getHeader("Accept-Encoding");
		return acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1;
	}

}
