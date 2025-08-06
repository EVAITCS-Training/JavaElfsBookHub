import { Route, Routes, useNavigate } from "react-router-dom"
import { Footer } from "./static/Footer"
import Navbar from "./static/Navbar"
import { LandingPage } from "./static/LandingPage"
import BookIndex from "./components/BookIndex"
import AddBook from "./components/AddBook"
import Register from "./components/Register"
import Login from "./components/Login"


function App() {
  const navigate = useNavigate();

  const onNavigate = (path:string):void => {
    navigate(path)
  }

  return (
    <>
      <Navbar onNavigate={onNavigate}/>
      <Routes>
        <Route path="/" element={<LandingPage/>} />
        <Route path="/books" element={<BookIndex/>} />
        <Route path="/add-book" element={<AddBook onNavigate={onNavigate}/>} />
        <Route path="/register" element={<Register onNavigate={onNavigate} />} />
        <Route path="/login" element={<Login onNavigate={onNavigate} />} />
      </Routes>
      <Footer />
    </>
  )
}

export default App
