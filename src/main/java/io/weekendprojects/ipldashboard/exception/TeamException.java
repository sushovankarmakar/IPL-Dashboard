package io.weekendprojects.ipldashboard.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
public class TeamException extends RuntimeException {

  private final String message;

}
