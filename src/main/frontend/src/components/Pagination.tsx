import React from 'react'

type Props = {
    totalPages: number;
    currentPage: number;
    setPage: React.Dispatch<React.SetStateAction<string>>;
    page: string;
}

const Pagination = (props: Props) => {

    const goToFirstPage = () => {
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=1");
        } else {
            props.setPage(props.page.substring(0,index) + "?page=1");
        };
    }

    const goToPreviousPage = () => {
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=" + (props.currentPage - 1).toString());
        } else {
            props.setPage(props.page.substring(0,index) + "?page=" + (props.currentPage - 1).toString());
        };
    }

    const goToNextPage = () => {
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=" + (props.currentPage + 1).toString());
        } else {
            props.setPage(props.page.substring(0,index) + "?page=" + (props.currentPage + 1).toString());
        };
    }

    const goToLastPage = () => {
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=" + props.totalPages);
        } else {
            props.setPage(props.page.substring(0,index) + "?page=" + props.totalPages);
        };
    }

    const changePage = (e: React.MouseEvent<HTMLElement>) => {
        const targetPage: string|null = e.currentTarget.textContent;
        const index = props.page.lastIndexOf('?');
        if ( index === -1){
            props.setPage(props.page + "?page=" + targetPage);
        } else {
            props.setPage(props.page.substring(0,index) + "?page=" + targetPage);
        };
        
    }

    const getPaginationGroup = () => {
        if (props.totalPages < 10) {
            return new Array(props.totalPages).fill(null).map((_, i) => i + 1);
        } else {
            if(props.currentPage > 4) {
                if (props.totalPages > props.currentPage + 3){
                    let pageArray = new Array(9).fill(null).map((_, i) => props.currentPage - 5 + i + 1)
                    pageArray[0]=-1;
                    pageArray[8]=-1;
                    return pageArray;
                } else {
                    let pageArray = new Array(9).fill(null).map((_, i) => props.totalPages - 9 + i + 1)
                    pageArray[0]=-1;
                    return pageArray;
                }
            }
            let pageArray = new Array(9).fill(null).map((_, i) => i + 1)
            pageArray[8]=-1;
            return pageArray;
        }
        return [-1];
    };

    if (props.totalPages < 2) return (null);

    return (
        <div className='pagination'>
            {(props.currentPage==1) ? (null) : <button className='prev' onClick={goToFirstPage}>&laquo;</button>} 
            {(props.currentPage==1) ? (null) : <button className='prev' onClick={goToPreviousPage}>&#8249;</button>} 
            {getPaginationGroup().map((item) => (<button className={`paginationItem ${props.currentPage === item ? 'active' : null} ${item === -1 ? 'dots' : null}`} key={item}  onClick={changePage} disabled={(item===props.currentPage)}>{(item===-1) ? <span>...</span> : <span>{item}</span> }</button>))}
            {(props.currentPage==props.totalPages) ? (null) : <button className='next' onClick={goToNextPage}>&#8250;</button>}
            {(props.currentPage==props.totalPages) ? (null) : <button className='next' onClick={goToLastPage}>&raquo;</button>} 
        </div>
    )
}

export default Pagination