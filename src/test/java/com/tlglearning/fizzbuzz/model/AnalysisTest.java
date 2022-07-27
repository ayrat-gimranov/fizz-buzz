package com.tlglearning.fizzbuzz.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

class AnalysisTest {

  static final Set<State> expectedFizz = EnumSet.of(State.FIZZ);
  static final Set<State> expectedFizzBuzz = EnumSet.of(State.FIZZ, State.BUZZ);
  static final Set<State> expectedBuzz = EnumSet.of(State.BUZZ);
  static final Set<State> expectedNeither = EnumSet.noneOf(State.class);

  final Analysis analysis = new Analysis();

  @ParameterizedTest
  @ValueSource(ints = {3, 21, 999_999_999})
  void analyze_fizz(int value) {
    assertEquals(expectedFizz, analysis.analyze(value));;
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 15, 999_999_990})
  void analyze_fizzBuzz(int value) {
    assertEquals(expectedFizzBuzz, analysis.analyze(value));;
  }

  @ParameterizedTest
  @ValueSource(ints = {5, 999_999_995})
  void analyze_Buzz(int value) {
    assertEquals(expectedBuzz, analysis.analyze(value));;
  }

  @ParameterizedTest
  @CsvFileSource(resources = "neither.csv")
  void analyze_neither(int value) {
    assertEquals(expectedNeither, analysis.analyze(value));;
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, -3, -5, -15})
  void analyze_negative(int value){
    try{
      analysis.analyze(value);
      fail();
    } catch (IllegalArgumentException e){
      // Do nothing; this is the expected behavior.
    }
  }

}