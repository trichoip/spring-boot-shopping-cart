package com.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler implements ProblemHandling {
    @ExceptionHandler
    public ResponseEntity<Problem> handleAuthenticationException(AuthenticationException e, NativeWebRequest request) {
        ThrowableProblem problem = Problem.builder()
                .with("timestamp", Instant.now())
                .with("error", Status.UNAUTHORIZED.getReasonPhrase())
                .withStatus(Status.UNAUTHORIZED)
                .withDetail(e.getMessage())
                .build();
        return create(problem, request);
    }
    @ExceptionHandler
    public ResponseEntity<Problem> handleAccessDeniedException(AccessDeniedException e, NativeWebRequest request) {
        ThrowableProblem problem = Problem.builder()
                .with("timestamp", Instant.now())
                .with("error", Status.FORBIDDEN.getReasonPhrase())
                .withStatus(Status.FORBIDDEN)
                .withDetail(e.getMessage())
                .build();
        return create(problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleResourceNotFoundException(ResourceNotFoundException e, NativeWebRequest request) {
        ThrowableProblem problem = Problem.builder()
                .with("timestamp", Instant.now())
                .with("error", Status.NOT_FOUND.getReasonPhrase())
                .withStatus(Status.NOT_FOUND)
                .withDetail(e.getMessage())
                .build();
        return create(problem, request);
    }
    @ExceptionHandler
    public ResponseEntity<Problem> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e, NativeWebRequest request) {
        ThrowableProblem problem = Problem.builder()
                .with("timestamp", Instant.now())
                .with("error", Status.CONFLICT.getReasonPhrase())
                .withStatus(Status.CONFLICT)
                .withDetail(e.getMessage())
                .build();
        return create(problem, request);
    }
}
