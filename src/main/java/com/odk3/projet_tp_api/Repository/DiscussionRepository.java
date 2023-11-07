package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.Discussion;
import com.odk3.projet_tp_api.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {
    public List<Discussion> findByForumIdForum(int idForum);

}
