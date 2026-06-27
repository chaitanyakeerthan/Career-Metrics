import "../styles/skillgap.css";
import logo from "../assets/AppLogo.jpeg";
import { useNavigate } from "react-router-dom";

function Sidebar() {

const navigate = useNavigate();

return (

<div className="sidebar">

<div className="sidebar-header">
<img src={logo} alt="logo" className="sidebar-logo"/>
<h2>Career Metrics</h2>
</div>

<ul className="sidebar-menu">

<li onClick={() => navigate('/dashboard')}>Dashboard</li>

<li onClick={() => navigate('/resume')}>Resume Analyzer</li>

<li onClick={() => navigate('/github')}>GitHub Insights</li>

<li onClick={() => navigate('/skill')}>Skills</li>

<li onClick={() => navigate('/Skill-Gap')}>Skill Gap</li>

<li onClick={() => navigate('/Career')}>Career Predictions</li>

<li onClick={() => navigate('/roadmap')}>Learning Roadmap</li>

<li onClick={() => navigate('/chatbot')}>AI Mentor</li>

</ul>

</div>

);

}

export default Sidebar;