package no.ntnu.idatt1002.demo.util;

/**
 * Class for verifying input in the data package. Includes checks for common things like checking if an input is empty.
 */
public class VerifyInput {

  /**
   * Checks if a string is null or empty
   *
   * @param input the string to be checked
   * @param parameter the name of the parameter
   * @throws IllegalArgumentException if the input is null or blank
   */
  public static void verifyNotEmpty(String input, String parameter) throws IllegalArgumentException {
    if (input == null || input.isBlank()) {
      throw new IllegalArgumentException("The input for the parameter: " + parameter + " cannot be null or blank");
    }
  }

  /**
   * Checks if an int is a positive number or minus one
   *
   * @param input the int to be checked
   * @param parameter the name of the parameter
   * @throws IllegalArgumentException if the int is not a positive number or minus one
   */
  public static void verifyPositiveNumberMinusOneAccepted(int input, String parameter) {
    if(input == 0|| input < -1) {
      throw new IllegalArgumentException("The input for the parameter: " + parameter + " must be a positive number or minus one");
    }
  }

  /**
   * Checks if an int is a positive number
   *
   * @param input the int to be checked
   * @param parameter the name of the parameter
   * @throws IllegalArgumentException if the int is not a positive number
   */
  public static void verifyPositiveNumberMinusOneNotAccepted(int input, String parameter) {
    if(input < 0) {
      throw new IllegalArgumentException("The input for the parameter: " + parameter + " must be a positive number");
    }
  }
}
