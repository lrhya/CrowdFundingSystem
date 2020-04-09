package com.lrh.crowd.funding.service.api;

import com.lrh.crowd.funding.entity.Depart;

import java.util.List;

/**
 * @author lrhya
 * @version 1.0
 * @date 2020/2/12 14:28
 */
public interface DepartService {
    List<Depart> getAll();

    void saveDepart(Depart depart);

    Depart getDepartById(Integer departId);

    void updateDepart(Depart depart);

    void removeDepart(Integer departId);
}
