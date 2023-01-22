package com.mkozi

object Main extends App:
  val fansiStr: fansi.Str = fansi.Color.Red("This should be a red string")

  println("Learning SBT!!!  It rocks!")
  println(fansiStr)

  import com.mkozi.core.Lib1
  Lib1.greeting
