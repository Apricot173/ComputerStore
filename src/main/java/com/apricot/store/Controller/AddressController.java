package com.apricot.store.Controller;

import com.apricot.store.Entity.Address;
import com.apricot.store.Entity.dto.AddressDto;
import com.apricot.store.Service.IAddressService;
import com.apricot.store.Utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;

    @PostMapping("/addNewAddress")
    public JsonResult addNewAdd(@RequestBody AddressDto dto, HttpSession session) {
        Address add = new Address(dto);
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.saveAddress(uid, username, add);
        JsonResult result = new JsonResult(SUCCESS_CODE, "新增地址成功", null);
        return result;
    }

    @GetMapping("/district")
    public JsonResult getDistrictByProvinceId(@RequestParam(name = "parent", required = false, defaultValue = "86") String parent) {
        //System.out.println("parent: " + parent);
        return new JsonResult(SUCCESS_CODE, "获取信息成功", addressService.getDistrictsByParent(parent));
    }

    @GetMapping("/queryAddresses")
    public JsonResult queryAddresses(HttpSession session) {
        Integer uid = getUserIdFromSession(session);
        List<Address> addresses = addressService.getAddressesByUid(uid);
        return new JsonResult(SUCCESS_CODE, "获取所有地址成功", addresses);
    }

    @PostMapping ("/setDefault/{addressID}")
    public JsonResult setDefault(/*@RequestParam(name = "aid")*/
                                 @PathVariable(name = "addressID") Integer aid,
                                 HttpSession session) {
        String username = getUsernameFromSession(session);
        Integer uid = getUserIdFromSession(session);
        addressService.setDefaultAddress(uid,username,aid);
        return new JsonResult(SUCCESS_CODE, "设置默认地址成功", null);
    }

    @DeleteMapping("/delete/{addressID}")
    public JsonResult deleteAddress(@PathVariable(name = "addressID") Integer aid,
                                    HttpSession session) {
        Integer uid = getUserIdFromSession(session);
        addressService.deleteAddress(uid, aid);
        return new JsonResult(SUCCESS_CODE, "删除地址成功", null);
    }
}
