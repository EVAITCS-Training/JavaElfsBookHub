import React, { useEffect, useState } from 'react'
import axios from 'axios'
import PostRow from './PostRow';

export default function PostIndex() {
    const [posts, setPosts] = useState([]);
    useEffect( () => {
        fetchData();
    }, [])
    const fetchData = async () => {
        await axios.get("https://dummyjson.com/posts")
        .then(res => {
            console.table(res);
            console.table(res.data.posts);
            setPosts(res.data.posts);
        })
    }


    useEffect(() => {
        console.log("Posts updated", posts)
    }, [posts])
  return (
    <div>
        <h2>Post Index</h2>
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Body</th>
                </tr>
            </thead>
            <tbody>
                {posts.map(post => {
                    return <PostRow post={post} key={post.id}/>
                })}
            </tbody>
        </table>
    </div>
  )
}
