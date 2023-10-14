//package com.example.QuanLyBanHang.apiController;
//
//import com.example.QuanLyBanHang.entity.Category;
//import com.example.QuanLyBanHang.entity.Order;
//import com.example.QuanLyBanHang.entity.Product;
//import com.example.QuanLyBanHang.entity.User;
//import com.example.QuanLyBanHang.service.*;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/v1/dashboard")
//public class ApiAdminController {
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    CategoryService categoryService;
//
//    @Autowired
//    Order_ItemService order_ItemService;
//
//    @Autowired
//    ProductImageService productImageService;
//
//    @Autowired
//    HttpSession session;
//    @Autowired
//    FileUpload cloudinary;
//    @GetMapping("dashboard/index")
//    public ResponseEntity<?>  DashboardView(Model model) {
//        User admin = (User) session.getAttribute("admin");
//        if (admin == null) {
//            return new ResponseEntity<>(null);
//        } else {
//            List<Order> listOrder = orderService.findAll();
//            List<Product> listProduct = productService.getAllProduct();
//            List<User> listUser = userService.findAll();
//            List<Category> listCategory = categoryService.findAll();
//
//            List<Order> recentOrders = orderService.findTop5RecentOrder();
//            List<String> recentUser = orderService.findTop5RecentCustomer();
//            List<User> recentCustomer = new ArrayList<>();
//            for (String y : recentUser) {
//                recentCustomer.add(userService.getUserByUser_nameandRole(y, "user"));
//            }
//            model.addAttribute("Total_Order", listOrder.size());
//
//
//            model.addAttribute("Total_Order", listOrder.size());
//            model.addAttribute("Total_Product", listProduct.size());
//            model.addAttribute("Total_User", listUser.size());
//            model.addAttribute("Total_Category", listCategory.size());
//            model.addAttribute("recentOrders", recentOrders);
////            return "dashboard/index";
//            return  null;
//        }
//    }
//}
