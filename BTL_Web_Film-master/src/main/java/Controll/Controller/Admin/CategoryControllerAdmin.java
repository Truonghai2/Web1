package Controll.Controller.Admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import Controll.Entity.Category;
import Controll.Entity.Movie;
import Controll.Service.CategoryService;
import Controll.Service.Impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CategoryControllerAdmin
 */
@WebServlet({"/categoryviews", "/categoryadd", "/categoryedit", "/categorydelete"})
public class CategoryControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CategoryService categoryService = new CategoryServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String path = request.getServletPath();
		
		switch (path) {
		case "/categoryviews":
			doGetViewsCategory(request, response);
			break;
		case "/categoryadd":
	        doGetAddCategory(request, response);
	        break;
		case "/categoryedit":
	        doGetEditCategory(request, response);
	        break;
		case "/categorydelete":
	        doGetDeleteCategory(request, response);
	        break;
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();

		switch (path) {
        case "/categoryadd":
            doPostNewCategory(request, response);
            break;
        case "/categoryedit":
            doPostEditCategory(request, response);
            break;
//        case "/editmoviedisabled":
//            doPostEditFilmDisable(request, response);
//            break;
        case "/categorydelete":
            doPostDeleteCategory(request, response);
            break;
//        case "/restoremovie":
//            doPostRestoreMovie(request, response);
//            break;
    }
}


	private void doGetViewsCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        List<Category> categorys;
        categorys = categoryService.findAll();
        System.out.print(categorys);
        request.setAttribute("categories", categorys);
        
        request.getRequestDispatcher("/views/admin/add-category.jsp").forward(request, response);
//        request.getRequestDispatcher("/views/admin/newFilm.jsp").forward(request, response);
	}
	
	private void doGetAddCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/newCategory.jsp").forward(request, response);	
	}
	
	private void doPostNewCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

        String name = request.getParameter("name");
      
        if (name != null && !name.isEmpty()) {
            try {
                categoryService.create(name);
                response.sendRedirect("categoryviews");
                session.setAttribute("addCategorySuccess", true);
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", "Category name already exists.");
                response.sendRedirect("categoryviews");
                session.setAttribute("addCategorySuccess", false);
            }
        } else {
            request.setAttribute("error", "Category name cannot be empty.");
            response.sendRedirect("categoryviews");
            session.setAttribute("addCategorySuccess", false);
     }    
}

	private void doGetEditCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer Id = Integer.parseInt(request.getParameter("Id"));
        Category category = categoryService.findById(Id);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/views/admin/EditCategory.jsp").forward(request, response);
	}
	
	private void doPostEditCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

	    Integer id =Integer.parseInt(request.getParameter("Id"));
	    String name = request.getParameter("name");
        if (name != null ) {

            Category categorysUpdate = categoryService.update(id ,name);
            if (categorysUpdate != null) {
                response.sendRedirect("categoryviews");
            }
        }
	}
	
	private void doGetDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        categoryService.delete(id);
        response.sendRedirect("categoryviews");
    }
	
	protected void doPostDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer id = Integer.parseInt(request.getParameter("Id"));

        Category category = categoryService.delete(id);

        if (category != null) {
            response.sendRedirect("categoryviews");
        }
    }
}
