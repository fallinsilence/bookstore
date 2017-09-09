package cn.hugang.ebookstore.servlet;

import cn.hugang.ebookstore.service.BookService;
import cn.hugang.ebookstore.utils.UUIDUtils;
import cn.hugang.ebookstore.vo.Book;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/7.
 */
@WebServlet(name = "FileUploadServlet",urlPatterns = "/upload")
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream os = null;
        Map<String, String> map = new HashMap<>();
        Book book = new Book();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload  fileUpload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = fileUpload.parseRequest(request);
            for (FileItem fileItem : list){
                if (fileItem.isFormField()){
                    String name = fileItem.getFieldName();
                    // 获得普通项的值
                    String value = fileItem.getString("UTF-8");
                    map.put(name, value);
                } else{
                    String fileName = fileItem.getName();
                    book.setImage("book_img/" + fileName);

                    InputStream is = fileItem.getInputStream();
                    String path = getServletContext().getRealPath("/book_img");
                    System.out.println(path);
                    os = new FileOutputStream(path + "/" + fileName);

                    int b;
                    while((b = is.read()) != -1){
                        os.write(b);
                    }
                }
            }
            BeanUtils.populate(book, map);
            book.setBid(UUIDUtils.getUUID());
            BookService service = new BookService();
            service.addBook(book);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally{
            if (os != null){
                os.close();
            }
            os = null;
        }

    }
}
