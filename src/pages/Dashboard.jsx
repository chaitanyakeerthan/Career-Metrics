import { useState } from "react";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import OverviewCards from "../components/OverViewCards";
import ProfileInputPanel from "../components/ProfileInputPanel";
import "../styles/Dashboard.css";

function Dashboard(){

const [dashboardData,setDashboardData] = useState(null);

return(

<div className="dashboard">

<Sidebar/>

<div className="main-section">

<Navbar/>

<div className="dashboard-content">

<h1 className="dashboard-title">
Career Metrics Dashboard
</h1>

<ProfileInputPanel
setDashboardData={setDashboardData}
/>

<OverviewCards
data={dashboardData}
/>

</div>

</div>

</div>

)

}

export default Dashboard;