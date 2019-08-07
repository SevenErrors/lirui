package com.lirui.work;

import com.lirui.work.service.BlessingService;

public class BirthdayBlessing {

    public static void main(String[] args) {
        BlessingService blessingService = new BlessingService();
        blessingService.sendEmail(blessingService.getEmployees());
    }
}
