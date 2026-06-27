import "../styles/Dashboard.css";
import { useState } from "react";
import axios from "axios";

function ProfileInputPanel({ setDashboardData }) {

const [github,setGithub] = useState("");
const [skills,setSkills] = useState("");
const [resume,setResume] = useState(null);

const handleAnalyze = async () => {

try{

const formData = new FormData();

formData.append("resume",resume);
formData.append("github",github);
formData.append("skills",skills);

const response = await axios.post(
"http://localhost:8080/api/profile/analyze",
formData
);

setDashboardData(response.data);

}catch(error){

console.error("Error analyzing profile",error);

}

};

return (

<div className="profile-panel">

<h2>Analyze Your Profile</h2>

<div className="profile-inputs">

<div className="input-group">
<label>Upload Resume</label>
<input
type="file"
onChange={(e)=>setResume(e.target.files[0])}
/>
</div>

<div className="input-group">
<label>GitHub Username</label>
<input
type="text"
placeholder="Enter GitHub username"
value={github}
onChange={(e)=>setGithub(e.target.value)}
/>
</div>

<div className="input-group">
<label>Manual Skills</label>
<input
type="text"
placeholder="Example: React, Java, Docker"
value={skills}
onChange={(e)=>setSkills(e.target.value)}
/>
</div>

<button
className="analyze-btn"
onClick={handleAnalyze}
>
Analyze Profile
</button>

</div>

</div>

)

}

export default ProfileInputPanel;