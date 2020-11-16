import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PostModel } from '../PostModel';
import { Observable } from 'rxjs';
import { createPostPayload } from '../post/create-post/create-post-payload';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  
  
  
  getAllPostsByTopic(topicID: Number):Observable<Array<PostModel>> {
    return this.httpclient.get<Array<PostModel>>("http://localhost:8484/api/posts/byTopic/"+topicID)
  }
 
  getAllByUsername(username:String):Observable<Array<PostModel>>{
    return this.httpclient.get<Array<PostModel>>("http://localhost:8484/api/posts/byUser/"+username);
  }

  constructor(private httpclient:HttpClient) {

   }

     getAllPosts():Observable<Array<PostModel>>
   {
    return this.httpclient.get<Array<PostModel>>("http://localhost:8484/api/posts/");
   }

   createPost(createPostPayload):Observable<createPostPayload>
   {
      return this.httpclient.post<createPostPayload>("http://localhost:8484/api/posts/",createPostPayload);
   }

   getPost(postId: Number):Observable<PostModel> {
    return this.httpclient.get<PostModel>("http://localhost:8484/api/posts/byPost/"+postId);
  }
}
