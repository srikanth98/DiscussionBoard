import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommentPayload } from '../comments/comment-payload';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient:HttpClient) { }

  getAllCommentsByPost(id:Number):Observable<Array<CommentPayload>>
  {
      return this.httpClient.get<Array<CommentPayload>>("http://localhost:8484/api/comments/byPost/"+id);
  }
  getAllCommentsByUser(userName:String):Observable<Array<CommentPayload>>
  {
      return this.httpClient.get<Array<CommentPayload>>("http://localhost:8484/api/comments/byUser/"+userName);
  }
  postComment(comment:CommentPayload):Observable<any>
  {
      return this.httpClient.post<CommentPayload>("http://localhost:8484/api/comments",comment);
  }
}
