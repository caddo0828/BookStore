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
 * 后台管理对书籍信息进行修改，以及添加。涉及到文件的上传操作，判断是修改类型还是添加书籍类型
 * @author 老腰
 */
@WebServlet("/ManaBookUpload.do")
public class ManaBookUpload extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//接收处理类型
		String type = request.getParameter("type");
		
		//判断请求是否是multipart
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new RuntimeErrorException(null, "当前请求不支持文件上传");
		}
		try {
			//创建一个FileItem工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置临时上传的大小
			factory.setSizeThreshold(1024 * 1024 * 1);
			//设置临时文件
			factory.setRepository(new File("D://images"));
			
			//创建文件上传下载的核心组件
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置每一个item的头部编码，可以防止文件名的中文乱码
			upload.setHeaderEncoding("utf-8");
			// 设置单个上传文件的最大边界值 2M
			upload.setFileSizeMax(1024*1024*1);
			// 设置一次上传所有文件的总和最大值为5M
			upload.setSizeMax(1024*1024*2);
			
			Book book = new Book();
			book.setId(UUIDImpl.getUID());
			//解析请求
			//获取所有的item
			List<FileItem> items = upload.parseRequest(request);
			// 遍历items集合
			for(FileItem fileitem : items) {
				// 判断表单提交的数据是普通的字段，还是文件表单项
				if(fileitem.isFormField()) {
					// 获得字段名和字段值
					String name = fileitem.getFieldName();
					//根据字段名获取对应的字段值
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
					String fileName = fileitem.getName();// 获取上传文件的原始文件名
					//截取文件名
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					//文件名唯一
					fileName = UUIDImpl.getUID()+ "_" + fileName;
					//在服务器创建文件
					// static/images/book/
					String webPath = "static/images/book/";
					book.setImgurl(webPath+fileName);
					//文件保存地
					String filepath = this.getServletContext().getRealPath("/"+webPath+fileName); 
					
					// 获取输入流，其中有上传文件的内容
					InputStream is = fileitem.getInputStream();
				
					// 创建文件输出流，将输入流读取的数据写入到具体的文件中
					OutputStream os = new FileOutputStream(filepath);
					int len = -1;
					byte[] bytes = new byte[1024];
					while ((len = is.read(bytes)) != -1) {
						os.write(bytes, 0, len);
					}
					// 关闭连接
					os.close();
					is.close();

					// 删除临时文件
					fileitem.delete();
				}
			}
			//将产品对象 保存到数据库
			if(type.equals("update")) {
				boolean flag = BookDao.updateBook(book);
				if(!flag) {
					request.setAttribute("error", "修改商品信息操作失败,请重新操作!");
					request.getRequestDispatcher("ManaFindBookServlet.do").forward(request, response);
					return ;
				}
			}else if(type.equals("add")) {
				boolean flag = BookDao.insertBook(book);
				if(!flag) {
					request.setAttribute("error", "添加商品操作失败,请重新操作!");
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
