import { Route, Routes, useNavigate } from "react-router-dom"
import { Footer } from "./static/Footer"
import Navbar from "./static/Navbar"
import { LandingPage } from "./static/LandingPage"
import BookIndex from "./components/BookIndex"


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
      </Routes>
      <Footer />
    </>
  )
}

export default App
