package qasession.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import qasession.entity.Answer;
import qasession.entity.QASession;
import qasession.entity.Question;
import qasession.exception.InvalidRequestException;
import qasession.service.QASessionService;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;


@RestController
public class QASessionController{

    @Autowired
    QASessionService qaSessionService;

    Logger logger = LoggerFactory.getLogger(QASessionController.class);

    @RequestMapping(path="/qa", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    public QASession createQASession(@RequestBody @Valid QASession qaSession) {
        logger.info("Adding QA Session");
            return qaSessionService.createQASssion(qaSession);

    }

    @RequestMapping(path="/qa/{qaSessionId}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QASession getQASession(@PathVariable int qaSessionId ) {
        logger.info("Retrieving QA Question");
        return qaSessionService.getQASession(qaSessionId);
    }

    @RequestMapping(path ="/qa/{qaSessionId}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public Question postQuestion(@PathVariable int qaSessionId, @RequestBody @Valid Question question) {
        logger.info("Post QA Question");
        return qaSessionService.postQuestion(qaSessionId, question);

    }

    @RequestMapping(path="/answer/{questionId}" , method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    public Answer postsAnswer(@PathVariable int questionId, @RequestBody @Valid Answer answer) {
        logger.info("Post QA Answer");
        return qaSessionService.answerQuestion(answer, questionId);
    }

    @RequestMapping(path="/qa/{qaSessionId}/questions" , method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Question> listQuestions(@PathVariable int qaSessionId,
                                        @QueryParam("answered") Boolean answered) {
        logger.info("List QA Questions");
        return qaSessionService.listQuestions(qaSessionId, answered);
    }




    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidRequestException.class)
    public List<String> handleValidationExceptions(InvalidRequestException ex)    {
        String message = ex.getMessage();
        List<String> messages =  new ArrayList() ;
        messages.add(ex.getMessage());
        return messages;

    }

}


