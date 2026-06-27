
import './App.css';

import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import ResumeUpload from "./pages/ResumeUpload";
import GithubInsights from "./pages/GithubInsights";
import SkillGap from "./pages/SkillGap";
import CareerSuggestions from "./pages/CareerSuggestions";
import Roadmap from "./pages/Roadmap";
import Chatbot from "./pages/Chatbot";
import Skills from './pages/Skills';

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path='/skill' element={<Skills/>}/>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/resume" element={<ResumeUpload />} />
        <Route path="/github" element={<GithubInsights />} />
        <Route path="/skill-gap" element={<SkillGap />} />
        <Route path="/career" element={<CareerSuggestions />} />
        <Route path="/roadmap" element={<Roadmap />} />
        <Route path="/chatbot" element={<Chatbot />} />
        

      </Routes>
    </BrowserRouter>
  );
}

export default App;
