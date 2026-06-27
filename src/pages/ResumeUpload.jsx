import { useState } from "react";
import axios from "axios";
import "../styles/ResumeUpload.css";

function ResumeAnalyzer(){

const [file,setFile] = useState(null);
const [jobDesc,setJobDesc] = useState("");
const [result,setResult] = useState(null);
const [loading,setLoading] = useState(false);

const handleAnalyze = async () => {

if(!file){
alert("Please upload a resume");
return;
}

if(!jobDesc){
alert("Please enter job description");
return;
}

try{

setLoading(true);

const formData = new FormData();
formData.append("resume",file);
formData.append("jobDescription",jobDesc);

const response = await axios.post(
"http://localhost:8080/api/resume/analyze",
formData,
{
headers:{
"Content-Type":"multipart/form-data"
}
}
);

setResult(response.data);

}catch(error){

console.error("Error analyzing resume:",error);

alert("Resume analysis failed. Check backend logs.");

}finally{

setLoading(false);

}

};

return(

<div className="resume-container">

<div className="resume-upload-panel">

<h2>Resume Analyzer</h2>

<div className="resume-input-group">
<label>Upload Resume</label>
<input
type="file"
onChange={(e)=>setFile(e.target.files[0])}
/>
</div>

<div className="resume-input-group">
<label>Job Description</label>
<textarea
rows="6"
value={jobDesc}
onChange={(e)=>setJobDesc(e.target.value)}
placeholder="Paste job description here"
/>
</div>

<button
className="resume-analyze-btn"
onClick={handleAnalyze}
disabled={loading}
>
{loading ? "Analyzing..." : "Analyze Resume"}
</button>

</div>

{/* RESULTS */}

{result && (

<div className="resume-results">

<div className="resume-card">
<h3>ATS Score</h3>
<p>{result.atsScore}%</p>
</div>

<div className="resume-card">
<h3>Missing Skills</h3>

<div className="resume-skill-list">

{result.missingSkills?.length > 0 ? (
result.missingSkills.map((skill,index)=>(
<span key={index}>{skill}</span>
))
) : (
<p>No missing skills 🎉</p>
)}

</div>

</div>

<div className="resume-card">
<h3>Suggestions</h3>

<ul className="resume-suggestions">

{result.suggestions?.length > 0 ? (
result.suggestions.map((item,index)=>(
<li key={index}>{item}</li>
))
) : (
<li>Your resume already matches the job well</li>
)}

</ul>

</div>

</div>

)}

</div>

);

}

export default ResumeAnalyzer;