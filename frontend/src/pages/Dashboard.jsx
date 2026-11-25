import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
export default function Dashboard() {
 const {user}=useAuth(); const admin=user.role==='ADMIN';
 return <><div className="welcome"><div><p className="eyebrow">OVERVIEW</p><h1>Hello, {user.name.split(' ')[0]} 👋</h1><p className="muted">Here is your {admin?'administration':'form'} workspace.</p></div><span className="welcome-icon">✦</span></div>
 <div className="row g-3 mt-2"><div className="col-md-4"><div className="stat-card"><span className="stat-icon purple">▤</span><h3>{admin?'Form management':'Available forms'}</h3><p>{admin?'Create and maintain dynamic forms.':'Browse forms and share your responses.'}</p><Link to={admin?'/admin/forms':'/forms'}>Open workspace →</Link></div></div>
 <div className="col-md-4"><div className="stat-card"><span className="stat-icon blue">↗</span><h3>{admin?'Review responses':'Your submissions'}</h3><p>{admin?'Inspect responses submitted by users.':'Submit forms published by administrators.'}</p><Link to={admin?'/admin/responses':'/forms'}>{admin?'View responses':'Browse forms'} →</Link></div></div>
 <div className="col-md-4"><div className="stat-card"><span className="stat-icon green">✓</span><h3>Role-based access</h3><p>You are signed in with the {user.role.toLowerCase()} role.</p><span className="text-success fw-semibold">Account active</span></div></div></div>
 {admin&&<div className="quick-panel mt-4"><div><h4>Build a new form</h4><p className="muted mb-0">Add fields, configure validation and publish it for users.</p></div><Link className="btn btn-primary" to="/admin/forms/new">+ Create form</Link></div>}</>;
}
