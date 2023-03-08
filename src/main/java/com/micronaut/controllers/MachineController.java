package com.micronaut.controllers;

import com.micronaut.etities.Machine;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/machine")
public class MachineController {

  @Post
  public HttpResponse<String> registerMachine(@Body Machine machine) {
    System.out.println("Maquina registrada " + machine.getMachineName());

    return HttpResponse.ok("Maquina registrada");
  }

  @Get
  public String ok() {
    return "ok";
  }

}
