package com.example.rotto.rabbitmqtutorial;

public interface RecvListener {

   void onMsgReceive(String message);
}
