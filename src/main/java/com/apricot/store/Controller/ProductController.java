package com.apricot.store.Controller;

import com.apricot.store.Entity.Product;
import com.apricot.store.Entity.dto.ProductSearchDto;
import com.apricot.store.Service.IProductService;
import com.apricot.store.Utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @GetMapping("/hotList")
    public JsonResult queryPrioritizedProducts() {
        return new JsonResult(SUCCESS_CODE, "获取4个优先商品成功",productService.queryPriorityProduct());
    }

    @GetMapping("/newList")
    public JsonResult queryNewestProducts() {
        return new JsonResult(SUCCESS_CODE, "获取4个最新商品成功",productService.queryNewProduct());
    }

    @GetMapping("/{id}/detail")
    public JsonResult queryProductDetail(@PathVariable("id") Integer id) {
        return new JsonResult(SUCCESS_CODE, "获取商品详情成功",productService.queryProductInfoById(id));
    }

    /**
     * Description : 处理根据产品关键字进行模糊查询的请求
     * @param title 查询的关键字
     **/
    @GetMapping("/search={title}")
    public JsonResult quertByTitle(@PathVariable("title") String title){
        //System.out.println("encoded title = " + title);
        //URLDecoder
        String d1title = URLDecoder.decode(title);
        String decodetitle = URLDecoder.decode(d1title);
        System.out.println("decoded title = " + decodetitle);
        List<Product> lists = productService.queryProductByTitle(decodetitle);
        List<ProductSearchDto> results = new ArrayList<>();
        // 将查询结果每个Product转为ProductSearchDto
        lists.forEach(product -> {
            ProductSearchDto productSearchDto = new ProductSearchDto();
            productSearchDto.setId(product.getId());
            productSearchDto.setTitle(product.getTitle());
            productSearchDto.setPrice(product.getPrice());
            productSearchDto.setSellPoint(product.getSellPoint());
            productSearchDto.setImage(product.getImage());
            results.add(productSearchDto);
        });
        return new JsonResult(SUCCESS_CODE,"查询成功",results);
    }
}
