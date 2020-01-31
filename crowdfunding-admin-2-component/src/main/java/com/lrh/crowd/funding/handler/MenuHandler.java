package com.lrh.crowd.funding.handler;

import com.lrh.crowd.funding.entity.Menu;
import com.lrh.crowd.funding.entity.ResultEntity;
import com.lrh.crowd.funding.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/1/31 13:28
 */
@RestController  //@ResponseBody+@Controller;
public class MenuHandler {


    @Autowired
    private MenuService menuService;


    @RequestMapping("/menu/get/whole/tree")
    public ResultEntity<Menu> getWholeTree() {

        // 1.查询所有的树形节点用于组装
        List<Menu> menuList = menuService.getAll();

        // 2.将List<Menu>转换为Map<Menu的id,Menu>
        Map<Integer, Menu> menuMap = new HashMap<>();

        for (Menu menu : menuList) {
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }

        // 3.声明变量用于存储根节点对象
        Menu rootNode = null;

        // 4.遍历List<Menu>
        for (Menu menu : menuList) {

            // 5.获取当前Menu对象的pid属性
            Integer pid = menu.getPid();

            // 6.判断pid是否为null
            if (pid == null) {

                // 7.如果pid为null，说明当前节点是根节点，所以赋值
                rootNode = menu;

                // 8.根节点没有父节点，所以不必找父节点组装，本次for循环停止执行，继续执行下一次循环
                continue;
            }

            // 9.既然pid不为null，那么我们根据这个pid查找当前节点的父节点。
            Menu father = menuMap.get(pid);

            // 10.组装：将menu添加到maybeFather的子节点集合中
            father.getChildren().add(menu);
        }

        return ResultEntity.successWithData(rootNode);
    }

    /*    public ResultEntity<Menu> getWholeTree() {

        // 1.查询所有的树形节点用于组装
        List<Menu> menuList = menuService.getAll();

        // 2.声明变量用于存储根节点对象
        Menu rootNode = null;

        // 3.遍历List<Menu>
        for (Menu menu : menuList) {

            // 4.获取当前Menu对象的pid属性
            Integer pid = menu.getPid();

            // 5.判断pid是否为null
            if(pid == null) {

                // 6.如果pid为null，说明当前节点是根节点，所以赋值
                rootNode = menu;

                // 7.根节点没有父节点，所以不必找父节点组装，本次for循环停止执行，继续执行下一次循环
                continue ;
            }

            // 8.既然pid不为null，那么我们根据这个pid查找当前节点的父节点。再次遍历menuList
            for (Menu maybeFather : menuList) {

                // 9.获取当前节点的id
                Integer id = maybeFather.getId();

                // 10.判断外层循环的pid是否等于内层循环的id
                if(Objects.equals(pid, id)) {

                    // 11.组装：将menu添加到maybeFather的子节点集合中
                    maybeFather.getChildren().add(menu);

                }
            }

        }

        return ResultEntity.successWithData(rootNode);
    }*/

}
