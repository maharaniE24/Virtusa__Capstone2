import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../api/axios';
export default function Forms({admin=false}) {
 const [forms,setForms]=useState([]); const [error,setError]=useState('');
 const load=()=>api.get(admin?'/forms':'/forms/published').then(r=>setForms(r.data)).catch(e=>setError(e.response?.data?.message||'Could not load forms'));
 useEffect(()=>{load()},[admin]);
 const remove=async id=>{if(!window.confirm('Delete this form?'))return;try{await api.delete(`/forms/${id}`);load()}catch(e){setError(e.response?.data?.message||'Delete failed')}};
 const toggle=async f=>{try{await api.put(`/forms/${f.id}`,{...f,published:!f.published});load()}catch(e){setError('Could not update form')}};
 return <><div className="page-heading"><div><p className="eyebrow">{admin?'ADMINISTRATION':'FORMS'}</p><h1>{admin?'Manage forms':'Available forms'}</h1><p className="muted">{admin?'Create, edit and publish forms.':'Choose a published form to submit a response.'}</p></div>{admin&&<Link className="btn btn-primary" to="/admin/forms/new">+ New form</Link>}</div>{error&&<div className="alert alert-danger">{error}</div>}<div className="row g-3">{forms.map(f=><div className="col-md-6 col-xl-4" key={f.id}><div className="form-card"><div className="d-flex justify-content-between"><span className="form-symbol">▤</span><span className={`status ${f.published?'published':'draft'}`}>{f.published?'Published':'Draft'}</span></div><h4 className="mt-3">{f.title}</h4><p className="muted">{f.description||'No description provided.'}</p><small className="muted">{f.fields?.length||0} fields</small><div className="card-actions">{admin?<><Link className="btn btn-sm btn-outline-primary" to={`/admin/forms/${f.id}/edit`}>Edit</Link><button className="btn btn-sm btn-outline-secondary" onClick={()=>toggle(f)}>{f.published?'Unpublish':'Publish'}</button><button className="btn btn-sm btn-outline-danger" onClick={()=>remove(f.id)}>Delete</button></>:<Link className="btn btn-sm btn-primary" to={`/forms/${f.id}`}>Open form →</Link>}</div></div></div>)}</div>{!forms.length&&!error&&<div className="empty-state">No forms found yet.</div>}</>;
}
