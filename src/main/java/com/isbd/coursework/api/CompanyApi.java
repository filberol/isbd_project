package com.isbd.coursework.api;

import com.isbd.coursework.entities.Company;

public interface CompanyApi {
    Company getCompanyById(Integer id);
    Company getCompanyByName(String name);
}
