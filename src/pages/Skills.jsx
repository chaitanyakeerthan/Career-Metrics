import { useEffect, useState } from "react";
import axios from "axios";
import "../styles/Skills.css";

function Skills() {

const [skills,setSkills] = useState([]);
const [loading,setLoading] = useState(true);
const [error,setError] = useState(null);

useEffect(()=>{

axios.get("http://localhost:8080/api/skills")
.then(res=>{
setSkills(res.data);
setLoading(false);
})
.catch(err=>{
console.error("Skill fetch error",err);
setError("Failed to load skills");
setLoading(false);
});

},[]);

if(loading){
return (
<div className="dashboard-content">
<h2 className="dashboard-title">Loading Skills...</h2>
</div>
);
}

if(error){
return (
<div className="dashboard-content">
<h2 className="dashboard-title">{error}</h2>
</div>
);
}

return(

<div className="dashboard-content">

<h2 className="dashboard-title">Your Skill Levels</h2>

<div className="skills-grid">

{skills.length === 0 ? (
<p className="no-skills">No skills detected from resume yet.</p>
) : (

skills.map((skill,index)=>(

<div key={index} className="skill-card">

<div className="skill-header">
<h3>{skill.name}</h3>
<span>{skill.level}%</span>
</div>

<div className="skill-bar">

<div
className="skill-progress"
style={{width:`${skill.level}%`}}
></div>

</div>

</div>

))

)}

</div>

</div>

);

}

export default Skills;