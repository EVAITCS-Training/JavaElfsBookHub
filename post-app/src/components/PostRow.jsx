import React from 'react'

export default function PostRow(props) {
  console.table(props)
  const {title, body} = props.post
  return (
    <tr>
      <td>{title}</td>
      <td>{body}</td>
    </tr>
  )
}
