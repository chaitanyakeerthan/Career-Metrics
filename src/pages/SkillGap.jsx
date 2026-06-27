import { useState } from "react";
import axios from "axios";
import "../styles/skillgap.css";

function SkillGap(){

const [file,setFile] = useState(null);
const [jobDesc,setJobDesc] = useState("");
const [missingSkills,setMissingSkills] = useState([]);
const [loading,setLoading] = useState(false);
const [message,setMessage] = useState("");

const analyzeGap = async () => {

if(!file){
alert("Please upload your resume");
return;
}

if(!jobDesc){
alert("Please enter job description");
return;
}

try{

setLoading(true);
setMessage("");
setMissingSkills([]);

const formData = new FormData();
formData.append("resume",file);
formData.append("jobDescription",jobDesc);

const response = await axios.post(
"http://localhost:8080/api/skillgap",
formData,
{
headers:{
"Content-Type":"multipart/form-data"
}
}
);

console.log("SkillGap Response:",response.data);

const skills = response.data.missingSkills || [];

if(skills.length === 0){
setMessage("Your resume already matches the job description.");
}else{
setMissingSkills(skills);
}

}catch(err){

console.error(err);
setMessage("Skill gap analysis failed");

}finally{

setLoading(false);

}

};

return(

<div className="dashboard-content">

<h2 className="dashboard-title">Skill Gap Analysis</h2>

<div className="skillgap-panel">

<label>Upload Resume</label>
<input
type="file"
onChange={(e)=>setFile(e.target.files[0])}
/>

<label>Job Description</label>
<textarea
rows="4"
placeholder="Paste job description here"
value={jobDesc}
onChange={(e)=>setJobDesc(e.target.value)}
></textarea>

<button
className="skillgap-btn"
onClick={analyzeGap}
disabled={loading}
>
{loading ? "Analyzing..." : "Analyze Skill Gap"}
</button>

</div>


{message && (
<p className="skillgap-message">{message}</p>
)}

{missingSkills.length > 0 && (

<div className="skillgap-results">

<h3>Missing Skills</h3>

<div className="skillgap-list">

{missingSkills.map((skill,index)=>(
<div key={index} className="skillgap-item">
{skill}
</div>
))}

</div>

</div>

)}

</div>

);

}

export default SkillGap;