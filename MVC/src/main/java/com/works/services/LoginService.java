package com.works.services;

import com.works.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class LoginService {

    final DBService dbService;

    public boolean login(Admin admin) {
        try {
            /*
            String query = " select * from admin where email = '"+admin.getEmail()+"' and password = '"+admin.getPassword()+"' ";
            System.out.println( query );
            Statement st = dbService.dataSource().getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs.next();
            */
            String query = "select * from admin where email = ? and password = ?";
            PreparedStatement st = dbService.dataSource().getConnection().prepareStatement(query);
            st.setString(1, admin.getEmail());
            st.setString(2, admin.getPassword());
            ResultSet rs = st.executeQuery();
            return rs.next();
        }catch (Exception ex) {
            System.err.println("login error : " + ex);
        }
        return false;
    }


}
