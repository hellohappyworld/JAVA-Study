package com.gaowj.fastScala

/**
  * 创建者：gaoweijian
  * 创建日期：2021-12-09
  * 功能描述：
  */
class Employee {
  override def toString: String = "Employee:" + super.toString;
}

object Employee {
  def main(args: Array[String]): Unit = {
    val employee: Employee = new Employee();
    println(employee.toString)
  }
}