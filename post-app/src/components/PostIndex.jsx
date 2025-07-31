import React, { useEffect, useState } from 'react'
import axios from 'axios'

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

            </tbody>
        </table>
    </div>
  )
}
