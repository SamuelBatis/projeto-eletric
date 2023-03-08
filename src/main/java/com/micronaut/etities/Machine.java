package com.micronaut.etities;

public class Machine {

  private Integer machineId;
  private String machineName;
  private Double powerWats;
  private Integer hourPerDay;

  public Machine(String machineName, Double powerWats, Integer hourPerDay) {
    this.machineName = machineName;
    this.powerWats = powerWats;
    this.hourPerDay = hourPerDay;
  }

  public Integer getMachineId() {
    return machineId;
  }

  public void setMachineId(Integer machineId) {
    this.machineId = machineId;
  }

  public String getMachineName() {
    return machineName;
  }

  public void setMachineName(String machineName) {
    this.machineName = machineName;
  }

  public Double getPowerWats() {
    return powerWats;
  }

  public void setPowerWats(Double powerWats) {
    this.powerWats = powerWats;
  }

  public Integer getHourPerDay() {
    return hourPerDay;
  }

  public void setHourPerDay(Integer hourPerDay) {
    this.hourPerDay = hourPerDay;
  }
}
