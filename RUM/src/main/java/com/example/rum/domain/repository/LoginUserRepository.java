package com.example.rum.domain.repository;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.rum.domain.entity.LoginUser;

@Repository
public class LoginUserRepository {

    private static final String SQL_FIND_BY_EMAIL = """
           SELECT
    			u.email,
    			u.name AS user_name,
    			u.password,
    			r.roll_name AS role_name
    		FROM T_USERS u
    		JOIN user_role ur
    			ON u.user_id = ur.user_id
    		JOIN M_ROLL r
    			ON ur.roll_id = r.roll_id
    		WHERE u.email = :email
            """;

    private static final ResultSetExtractor<LoginUser> LOGIN_USER_EXTRACTOR = (rs) -> {
        String email = null;
        String userName = null;
        String password = null;
        List<String> roleList = new ArrayList<>();
        while (rs.next()) {
            if (email == null) {
                email = rs.getString("email");
                userName = rs.getString("user_name");
                password = rs.getString("password");
            }
            roleList.add(rs.getString("role_name"));
        }
        if (email == null) {
            return null;
        }
        return new LoginUser(email, userName, password, roleList);
    };

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LoginUserRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Optional<LoginUser> findByEmail(String email) {
        MapSqlParameterSource params = new MapSqlParameterSource("email", email);
        LoginUser loginUser = namedParameterJdbcTemplate.query(SQL_FIND_BY_EMAIL, params, LOGIN_USER_EXTRACTOR);
        return Optional.ofNullable(loginUser);
    }
}
