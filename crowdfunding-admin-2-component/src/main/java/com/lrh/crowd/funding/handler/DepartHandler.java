package com.lrh.crowd.funding.handler;


import com.lrh.crowd.funding.entity.Depart;
import com.lrh.crowd.funding.entity.ResultEntity;
import com.lrh.crowd.funding.service.api.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/12 14:28
 */
@RestController
public class DepartHandler {

    @Autowired
    private DepartService departService;


    @RequestMapping("/depart/remove/{departId}")
    public ResultEntity<String> removeDepart(@PathVariable("departId") Integer departId) {

        departService.removeDepart(departId);

        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/depart/update")
    public ResultEntity<String> updateDepart(Depart depart) {

        departService.updateDepart(depart);

        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/depart/get/{departId}")
    public ResultEntity<Depart> getDepartById(@PathVariable("departId") Integer departId) {

        Depart depart = departService.getDepartById(departId);

        return ResultEntity.successWithData(depart);
    }
    

    @RequestMapping("/depart/get/whole/tree")
    public ResultEntity<Depart> getWholeTree() {

        // 1.查询所有的树形节点用于组装
        List<Depart> departList = departService.getAll();

        // 2.将List<Depart>转换为Map<Depart的id,Depart>
        Map<Integer, Depart> departMap = new HashMap<>();

        for (Depart depart : departList) {
            Integer id = depart.getId();
            departMap.put(id, depart);
        }

        // 3.声明变量用于存储根节点对象
        Depart rootNode = null;

        // 4.遍历List<Depart>
        for (Depart depart : departList) {

            // 5.获取当前Depart对象的pid属性
            Integer pid = depart.getPid();

            // 6.判断pid是否为null
            if (pid == null) {

                // 7.如果pid为null，说明当前节点是根节点，所以赋值
                rootNode = depart;

                // 8.根节点没有父节点，所以不必找父节点组装，本次for循环停止执行，继续执行下一次循环
                continue;
            }

            // 9.既然pid不为null，那么我们根据这个pid查找当前节点的父节点。
            Depart father = departMap.get(pid);

            // 10.组装：将depart添加到maybeFather的子节点集合中
            father.getChildren().add(depart);
        }

        return ResultEntity.successWithData(rootNode);
    }

    @RequestMapping("/depart/save")
    public ResultEntity<String> saveDepart(Depart depart) {

        departService.saveDepart(depart);

        return ResultEntity.successWithoutData();
    }
}
