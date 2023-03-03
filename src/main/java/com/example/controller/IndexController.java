package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.BoardService;
import com.example.vo.Board;
import com.example.vo.Reply;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	
    @Autowired
    private BoardService s;
	
    @RequestMapping(value="/boardList", method=RequestMethod.GET)
    @ResponseBody
    public List<Board> boardList(){
        return s.getBoard();
    }
    
    @RequestMapping(value="/writeAction", method=RequestMethod.POST)
    @ResponseBody
    public String writeAction(HttpServletRequest request, 
    		@RequestParam("title") String title, 
    		@RequestParam("contents") String contents) {
    	Board b = new Board();
    	b.setTitle(title);
    	b.setContents(contents);
    	b.setImage("nofile");
    	
    	s.addBoard(b);
    	return "board";
    }
    
    @RequestMapping(value="/boardView", method=RequestMethod.GET)
    @ResponseBody
    public Board boardView(@RequestParam("idx") int idx) {

    	return s.getBoardOne(idx);
    }
    
    @RequestMapping(value="/replyList", method=RequestMethod.GET)
    @ResponseBody
    public List<Reply> replyList(@RequestParam("idx")int boardIdx){
        return s.getReply(boardIdx);
    }

    @RequestMapping(value="/writeReply", method=RequestMethod.POST)
    @ResponseBody
    public String writeReply(
            @RequestParam("idx")int idx,
            @RequestParam("replyIdx")int replyIdx,
            @RequestParam("contents")String contents) {
    	Reply reply = new Reply();
    	reply.setIdx(idx);
    	reply.setReplyIdx(replyIdx);
    	reply.setContents(contents);
    	s.addReply(reply);
        return "reply";
    }
}
