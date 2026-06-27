import { useState } from "react";
import axios from "axios";
import "../styles/Roadmap.css";

function LearningRoadmap(){

const [skills,setSkills] = useState("");
const [roadmap,setRoadmap] = useState([]);
const [loading,setLoading] = useState(false);
const [message,setMessage] = useState("");

const generateRoadmap = async () => {

if(!skills){
setMessage("Please enter missing skills");
return;
}

try{

setLoading(true);
setMessage("");
setRoadmap([]);

const response = await axios.post(
"http://localhost:8080/api/roadmap",
{
skills: skills
}
);

setRoadmap(response.data);

if(!response.data || response.data.length === 0){
setMessage("No roadmap generated.");
}

}catch(error){

console.error(error);
setMessage("Failed to generate roadmap.");

}finally{

setLoading(false);

}

};

return(

<div className="dashboard-content">

<h2 className="dashboard-title">Learning Roadmap</h2>

<div className="roadmap-panel">

<div className="roadmap-input">

<label>Missing Skills</label>

<textarea
placeholder="Example: Docker, AWS, Kubernetes"
value={skills}
onChange={(e)=>setSkills(e.target.value)}
/>

</div>

<button
className="roadmap-btn"
onClick={generateRoadmap}
disabled={loading}
>
{loading ? "Generating..." : "Generate Roadmap"}
</button>

</div>

{message && <p className="roadmap-message">{message}</p>}

{roadmap.length > 0 && (

<div className="roadmap-results">

<h3>Recommended Learning Path</h3>

{roadmap.map((item,index)=>(

<div key={index} className="roadmap-card">

<h4>{item.skill}</h4>

<ul>

{item.steps.map((step,i)=>(
<li key={i}>{step}</li>
))}

</ul>

</div>

))}

</div>

)}

</div>

);

}

export default LearningRoadmap;
