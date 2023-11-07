package com.odk3.projet_tp_api.Repository;

import com.odk3.projet_tp_api.model.EmailDetails;

public interface EmailDetailsRepository {
    String sendSimpleMail(EmailDetails details);
}