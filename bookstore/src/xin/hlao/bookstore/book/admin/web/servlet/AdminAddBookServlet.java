package xin.hlao.bookstore.book.admin.web.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;
import xin.hlao.bookstore.book.domain.Book;
import xin.hlao.bookstore.book.service.BookService;
import xin.hlao.bookstore.category.domain.Category;
import xin.hlao.bookstore.category.service.CategoryService;


//管理员添加图书
public class AdminAddBookServlet extends HttpServlet {
	private BookService bs=new BookService();
	private CategoryService cs=new CategoryService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Category> categorylist=cs.findallcategory();
		Book book=new Book();
		
		//创建工厂
		DiskFileItemFactory factory=new DiskFileItemFactory(15*1024,new File("D:/temp"));
		//创建解析器
		ServletFileUpload sfl=new ServletFileUpload(factory);
		//设置文件最大为100KB
		sfl.setFileSizeMax(15*1024);
		//解析request成一个List<FileItem>
		try {
			List<FileItem> fileitemlist = sfl.parseRequest(request);
			Map<String, String>map=new HashMap<String, String>();
			for (FileItem fileItem : fileitemlist) {
				//判断是否为普通表单项，如果是就封装到Book类中
				if(fileItem.isFormField()) {
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
				}
			} 
			 book=CommonUtils.toBean(map, Book.class);
			
			String bname=book.getBname();
			Map<String, String> error=new HashMap<String,String>();
			if(bname.trim().isEmpty()||bname==null) {
				error.put("bname", "不能为空！");
			}
			Double price=book.getPrice(); 
			if(price==null) {
				error.put("price", "不能为空！");
			}else if(price<=0) {
				error.put("price", "价格不能少于0");
			}
			String author=book.getAuthor();
			if(author.trim().isEmpty()||author==null) {
				error.put("author", "不能为空！");
			}
			if(error.size()>0) {
				request.setAttribute("error", error);
				request.setAttribute("book",book);
				request.setAttribute("categorylist", categorylist);
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			String cid=map.get("cid");
			Category category=cs.findonecategory(cid); 
			book.setCategory(category);
			
			//得到保存目录
			String savepath=this.getServletContext().getRealPath("/book_img");

			//得到文件的名称
			String filename=CommonUtils.uuid()+"_"+fileitemlist.get(0).getName();
			filename=filename.substring(filename.lastIndexOf("\\")+1); 

			
			//验证图片是否为JPG扩展名
			if(!filename.toLowerCase().endsWith("jpg")) {
				request.setAttribute("msg", "您上传的图片不是JPG扩展名！");
				request.setAttribute("book", book);
				request.setAttribute("categorylist", categorylist);
				request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				return;
			}
			
			//设置Book对象image，即把图片的路径设置给Book的image
			book.setImage("book_img/"+filename);
			book.setBid(CommonUtils.uuid());
//			
			//使用目录和文件名创建目标文件
			File file=new File(savepath, filename);
			//保存上传文件的目标位置
				fileitemlist.get(0).write(file); 
				
				
				bs.addbook(book);
				
				request.getRequestDispatcher("/Admin/AdminBookServlet?method=findallbook").forward(request, response);
				}catch (Exception e) {
					if(e instanceof FileUploadBase.FileSizeLimitExceededException) {
						request.setAttribute("msg", "你超出的文件超过15KB！");
						request.setAttribute("categorylist", categorylist);
						request.setAttribute("book", book);
						request.getRequestDispatcher("/adminjsps/admin/book/add.jsp").forward(request, response);
				}
			}
		}
	}