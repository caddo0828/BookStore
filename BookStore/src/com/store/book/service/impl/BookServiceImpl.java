package com.store.book.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.store.book.dao.BookDao;
import com.store.book.dao.impl.BookDaoImpl;
import com.store.book.domain.Book;
import com.store.book.service.BookService;
import com.store.web.service.UUIDImpl;

public class BookServiceImpl implements BookService{
	private BookDao dao = new BookDaoImpl();
	
	@Override
	public int getPageCount() {
		return dao.getPageCount();
	}

	@Override
	public ArrayList<Book> findAllBook(int pageNumber) {
		return dao.findAllBook(pageNumber);
	}

	@Override
	public Book findById(String id) {
		return dao.findById(id);
	}

	@Override
	public void updateNums(Book book) {
		dao.updateNums(book);
	}

	@Override
	public boolean deleteBookById(String bid) {
		return dao.deleteBookById(bid);
	}

	@Override
	public boolean updateBook(Book book) {
		return dao.updateBook(book);
	}

	@Override
	public boolean insertBook(Book book) {
		return dao.insertBook(book);
	}

	@Override
	public ArrayList<Book> searchByIdOrName(String bookId, String bookName) {
		return dao.searchByIdOrName(bookId, bookName);
	}

	@Override
	public Book fileUploadBook(HttpServletRequest request, HttpServletResponse response) {
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
					String filepath = request.getServletContext().getRealPath("/"+webPath+fileName); 
					
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
			return book;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	
	}

}
