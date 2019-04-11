package com.store.manager.book.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.store.book.dao.BookDao;
import com.store.book.domain.Book;
import com.store.web.listener.CountListener;
import com.store.web.service.UUIDImpl;
/**
 * ��̨������鼮��Ϣ�����޸ģ��Լ���ӡ��漰���ļ����ϴ��������ж����޸����ͻ�������鼮����
 * @author ����
 */
@WebServlet("/ManaBookUpload.do")
public class ManaBookUpload extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//���մ�������
		String type = request.getParameter("type");
		
		//�ж������Ƿ���multipart
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeErrorException(null, "��ǰ����֧���ļ��ϴ�");
		}
		try {
			//����һ��FileItem����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//������ʱ�ϴ��Ĵ�С
			factory.setSizeThreshold(1024 * 1024 * 1);
			//������ʱ�ļ�
			factory.setRepository(new File("D://images"));
			
			//�����ļ��ϴ����صĺ������
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ����ÿһ��item��ͷ�����룬���Է�ֹ�ļ�������������
			upload.setHeaderEncoding("utf-8");
			// ���õ����ϴ��ļ������߽�ֵ 2M
			upload.setFileSizeMax(1024*1024*1);
			// ����һ���ϴ������ļ����ܺ����ֵΪ5M
			upload.setSizeMax(1024*1024*2);
			
			Book book = new Book();
			book.setId(UUIDImpl.getUID());
			//��������
			//��ȡ���е�item
			List<FileItem> items = upload.parseRequest(request);
			// ����items����
			for(FileItem fileitem : items) {
				// �жϱ��ύ����������ͨ���ֶΣ������ļ�����
				if(fileitem.isFormField()) {
					// ����ֶ������ֶ�ֵ
					String name = fileitem.getFieldName();
					//�����ֶ�����ȡ��Ӧ���ֶ�ֵ
					switch (name) {
					case "bid":
						book.setId(fileitem.getString("utf-8"));
						break;
					case "bookName":
						book.setName(fileitem.getString("utf-8"));
						break;
					case "price":
					    double price = Double.parseDouble(fileitem.getString("utf-8"));
					    book.setPrice(price);
						break;
					case "nums":
						int num = Integer.parseInt(fileitem.getString("utf-8"));
						book.setNums(num);
						break;
					case "author":
						book.setAuthor(fileitem.getString("utf-8"));
						break;
					case "category":
						book.setCategory(fileitem.getString("utf-8"));
						break;
					case "description":
						book.setDescription(fileitem.getString("utf-8"));
						break;
					default:
						break;
					}
				}else {
					String fileName = fileitem.getName();// ��ȡ�ϴ��ļ���ԭʼ�ļ���
					//��ȡ�ļ���
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					//�ļ���Ψһ
					fileName = UUIDImpl.getUID()+ "_" + fileName;
					//�ڷ����������ļ�
					// static/images/book/
					String webPath = "static/images/book/";
					book.setImgurl(webPath+fileName);
					//�ļ������
					String filepath = this.getServletContext().getRealPath("/"+webPath+fileName); 
					
					// ��ȡ���������������ϴ��ļ�������
					InputStream is = fileitem.getInputStream();
				
					// �����ļ������������������ȡ������д�뵽������ļ���
					OutputStream os = new FileOutputStream(filepath);
					int len = -1;
					byte[] bytes = new byte[1024];
					while ((len = is.read(bytes)) != -1) {
						os.write(bytes, 0, len);
					}
					// �ر�����
					os.close();
					is.close();

					// ɾ����ʱ�ļ�
					fileitem.delete();
				}
			}
			//����Ʒ���� ���浽���ݿ�
			if(type.equals("update")) {
				boolean flag = BookDao.updateBook(book);
				if(!flag) {
					request.setAttribute("error", "�޸���Ʒ��Ϣ����ʧ��,�����²���!");
					request.getRequestDispatcher("ManaFindBookServlet.do").forward(request, response);
					return ;
				}
			}else if(type.equals("add")) {
				boolean flag = BookDao.insertBook(book);
				if(!flag) {
					request.setAttribute("error", "�����Ʒ����ʧ��,�����²���!");
					request.getRequestDispatcher("Manager/book-add.jsp").forward(request, response);
					return ;
				}
			}
			request.getRequestDispatcher("Manager/manage-result.html").forward(request, response);
			return ;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
