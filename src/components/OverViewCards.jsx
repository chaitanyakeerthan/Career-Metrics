import "../styles/Dashboard.css";

function OverviewCards({ data }) {

if(!data) return null;

return (

<div className="cards">

<div className="card">
<h3>Total Skills</h3>
<p>{data.totalSkills}</p>
</div>

<div className="card">
<h3>Resume Score</h3>
<p>{data.resumeScore}%</p>
</div>

<div className="card">
<h3>GitHub Repositories</h3>
<p>{data.githubRepos}</p>
</div>

<div className="card">
<h3>GitHub Activity</h3>
<p>{data.githubActivity}</p>
</div>

<div className="card">
<h3>Skill Match</h3>
<p>{data.skillMatch}%</p>
</div>

<div className="card">
<h3>Missing Skills</h3>

<div className="card-details">

{data.missingSkills.map((skill,index)=>(
<span key={index}>{skill}</span>
))}

</div>

</div>

<div className="card">
<h3>Career Readiness</h3>
<p>{data.careerReadiness}%</p>
</div>

</div>

);

}

export default OverviewCards;