package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.CategoryDto;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateCategory;
import com.example.QuanLyBanHang.entity.Category;
import com.example.QuanLyBanHang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController

@RequestMapping("/api/v1/category")
public class APICategoryController {

    @Autowired
    CategoryService categoryService;
    @GetMapping
    ResponseEntity<?> getALLCategory()
    {
        List<Category> categoryList = categoryService.findAll();
        ArrayList<CategoryDto> categoryDtos = new ArrayList<>();
        for (Category category : categoryList)
        {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getCategory_Name());
            categoryDtos.add(categoryDto);
        }
        return new ResponseEntity<>(categoryDtos , HttpStatus.OK);
    }
    @GetMapping(value ="/{id}")
    ResponseEntity<?> getCategoryById(@PathVariable int id)
    {
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getCategory_Name());
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> CreateCategory(@RequestBody FormCreateCategory form)
    {
//        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
//        {
//            return new ResponseEntity<>("Không có quyền thêm danh mục" , HttpStatus.OK);
//        }
        Category category = categoryService.CreateCategory(form);
    return new ResponseEntity<Category>(category , HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> DeleteCategory(@PathVariable int id)
    {
        categoryService.deleteCategoryId(id);
        return new ResponseEntity<>("đã xóa thành công ",HttpStatus.OK );
    }
}
