import React from 'react'

export default function PostRow({title, body}) {
  return (
    <tr>
      <td>{title}</td>
      <td>{body}</td>
    </tr>
  )
}
