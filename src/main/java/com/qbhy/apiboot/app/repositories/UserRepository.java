package com.qbhy.apiboot.app.repositories;

import com.qbhy.apiboot.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
